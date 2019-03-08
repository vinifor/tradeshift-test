package com.tradeshift.test.endpoint;

import com.tradeshift.test.model.Node;
import com.tradeshift.test.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("nodes")
public class NodeEndpoint {

    @Autowired
    private NodeRepository repository;

    @GetMapping(path = "/{nodeId}/children")
    private ResponseEntity<?> getNodeChildren(@PathVariable("nodeId") Long nodeId) {
        Node node = repository.findOne(nodeId);

        return node != null
                ? new ResponseEntity<>(repository.findOne(nodeId).getChildren(), HttpStatus.OK)
                : new ResponseEntity<>("Node not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/{nodeId}/parent/{parentId}")
    private ResponseEntity<?> setNodeParent(@PathVariable("nodeId") Long nodeId, @PathVariable("parentId") Long parentId) {
        Node node = repository.findOne(nodeId);
        if (node == null) {
            return new ResponseEntity<>("Node not found", HttpStatus.NOT_FOUND);
        }

        Node parent = repository.findOne(parentId);
        if (parent == null) {
            return new ResponseEntity<>("Parent not found", HttpStatus.NOT_FOUND);
        }

        node.setParent(parent);
        node.setHeight(parent.getHeight() + 1);

        return new ResponseEntity<>(repository.save(node), HttpStatus.OK);
    }
}
