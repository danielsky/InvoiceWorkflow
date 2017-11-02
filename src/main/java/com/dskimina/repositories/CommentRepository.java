package com.dskimina.repositories;

import com.dskimina.data.Comment;
import com.dskimina.data.ServiceRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByServiceRequest(ServiceRequest serviceRequest);
}
