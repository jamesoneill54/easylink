package com.neueda.easylink.controller;

import com.neueda.easylink.model.CreationRequest;
import com.neueda.easylink.model.CreationResponse;
import com.neueda.easylink.model.EasyLink;
import com.neueda.easylink.repository.EasyLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
class EasyLinkController {

    private final EasyLinkRepository repository;

    @Autowired
    private EasyLinkController(EasyLinkRepository repository, Environment environment) {
        this.repository = repository;
    }

    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity handleIdNotFound() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreationResponse> generateEasyLink(@RequestBody CreationRequest creationRequest) {
        EasyLink easyLink = new EasyLink(creationRequest.getLink());
        while (repository.findById(easyLink.getId()) != null) {
            easyLink = new EasyLink(creationRequest.getLink());
        }
        repository.save(easyLink);
        return ResponseEntity.ok(new CreationResponse("http://localhost:8081/" + easyLink.getId()));
    }

    @GetMapping("/{easyLinkId}")
    public RedirectView redirectToOriginalLink(@PathVariable String easyLinkId) throws NoSuchFieldException {
        EasyLink easyLink = repository.findById(easyLinkId);
        if (easyLink == null) {
            throw new NoSuchFieldException("ID not found in repository.");
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(easyLink.getRedirectLink());
        return redirectView;
    }
}
