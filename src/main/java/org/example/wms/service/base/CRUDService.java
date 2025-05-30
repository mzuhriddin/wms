package org.example.wms.service.base;

import lombok.extern.slf4j.Slf4j;
import org.example.wms.dto.general.ApiResponse;
import org.example.wms.dto.general.BaseDTO;
import org.example.wms.dto.general.PaginationDTO;
import org.example.wms.mapper.base.BaseMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class CRUDService<R extends JpaRepository<E, Long>, E, D extends BaseDTO, M extends BaseMapper<E, D>> {
    protected R repository;
    protected M mapper;

    protected CRUDService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ApiResponse<List<D>> getAll(PaginationDTO pagination) {
        PageRequest pageRequest = PageRequest.of(pagination.getPage() - 1, pagination.getSize());
        pageRequest.withSort(Boolean.TRUE.equals(pagination.getDescending()) ? Sort.Direction.DESC : Sort.Direction.ASC, pagination.getSort());

        List<D> list = repository.findAll(pageRequest).stream()
                .map(mapper::toDTO)
                .toList();
        long total = repository.count();

        logging(String.format("getAll result: list.size: %d, total: %d", list.size(), total));
        return new ApiResponse<>(200, "SUCCESS", list, total);
    }

    public ApiResponse<D> getById(Long id) {
        ApiResponse<D> response = repository.findById(id).map(mapper::toDTO)
                .map(dto -> new ApiResponse<>(200, "SUCCESS", dto, 0))
                .orElseGet(() -> new ApiResponse<>(404, "NOT FOUND"));
        logging(String.format("getById: id: %d, result: %s", id, response));
        return response;
    }

    public ApiResponse<D> create(D dto) {
        ApiResponse<D> response = new ApiResponse<>(200, "SUCCESS", mapper.toDTO(repository.save(mapper.toEntity(dto))), 0);
        logging(String.format("SUCCESS: create: dto: %s", response.getData()));
        return response;
    }

    public ApiResponse<D> update(Long id, D dto) {
        Optional<E> optional = repository.findById(id);
        if (optional.isPresent()) {
            dto.setId(id);
            E entity = mapper.toEntity(dto);
            E save = repository.save(entity);
            logging(String.format("SUCCESS: update:  dto: %s", dto));
            return new ApiResponse<>(200, "SUCCESS", mapper.toDTO(save), 0);
        }
        logging(String.format("ERROR: NOT FOUND: update: id: %d", id));
        return new ApiResponse<>(404, "NOT FOUND");
    }

    public ApiResponse<Void> delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            logging(String.format("SUCCESS: delete: id: %d", id));
            return new ApiResponse<>(200, "SUCCESS");
        }
        logging(String.format("ERROR: NOT FOUND: delete: id: %d", id));
        return new ApiResponse<>(404, "NOT FOUND");
    }

    private void logging(String message) {
        StackTraceElement stack = Thread.currentThread().getStackTrace()[3];
        String className = stack.getClassName();
        int lineNumber = stack.getLineNumber();

        log.info("SUCCESS - {}[{}]: {}", className, lineNumber, message);
    }
}
