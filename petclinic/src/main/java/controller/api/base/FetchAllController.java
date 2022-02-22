package controller.api.base;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FetchAllController<IN, OUT> extends BaseController<IN, OUT> {
    ResponseEntity<List<OUT>> fetchAllEntities();
}
