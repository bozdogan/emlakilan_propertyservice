package org.bozdgn.propertyservice.controller;

import org.bozdgn.propertyservice.dto.PendingResponse;
import org.bozdgn.propertyservice.dto.PropertyOutput;
import org.bozdgn.propertyservice.dto.UpdateOutput;
import org.bozdgn.propertyservice.model.PropertyApprovalStatus;
import org.bozdgn.propertyservice.service.PropertyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApprovalController {
    private static final String APPROVAL_URL_TEMPLATE = "/api/approve/%s";
    private static final String REJECTION_URL_TEMPLATE = "/api/reject/%s";

    private final PropertyService propertyService;

    public ApprovalController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/list-all")
    public List<PropertyOutput> getAll() {
        return propertyService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/list-pending")
    public List<PendingResponse> getPending() {
        return propertyService.getPendingProperties().stream()
                .map(it -> new PendingResponse(
                        it,
                        String.format(APPROVAL_URL_TEMPLATE, it.getId()),
                        String.format(REJECTION_URL_TEMPLATE, it.getId())))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/list-rejected")
    public List<PropertyOutput> getRejected() {
        return propertyService.getRejectedProperties();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/approve/{id}")
    public UpdateOutput acceptProperty(@PathVariable Long id) {
        return propertyService.changeApprovalStatus(id, PropertyApprovalStatus.ACCEPTED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/reject/{id}")
    public UpdateOutput rejectProperty(@PathVariable Long id) {
        return propertyService.changeApprovalStatus(id, PropertyApprovalStatus.REJECTED);
    }
}
