package com.dskimina.services;

import com.dskimina.data.Comment;
import com.dskimina.data.ServiceRequest;
import com.dskimina.data.User;
import com.dskimina.forms.CommentForm;
import com.dskimina.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public String createComment(CommentForm commentForm, User author, ServiceRequest serviceRequest){
        Comment comment = new Comment();
        comment.setIdentifier(UUID.randomUUID().toString());
        comment.setContent(commentForm.getContent());
        comment.setAuthor(author);
        comment.setCreationDate(new Date());
        comment.setServiceRequest(serviceRequest);
        comment = commentRepository.save(comment);
        return comment.getIdentifier();
    }

    public List<Comment> getCommentsForServiceRequest(ServiceRequest serviceRequest) {
        return commentRepository.findByServiceRequest(serviceRequest);
    }
}
