package com.dskimina.repositories;

import com.dskimina.data.Comment;
import com.dskimina.data.ServiceRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByServiceRequest(ServiceRequest serviceRequest);
}
