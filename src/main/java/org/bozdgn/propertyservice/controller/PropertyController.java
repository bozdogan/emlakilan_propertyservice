package org.bozdgn.propertyservice.controller;

import org.bozdgn.propertyservice.dto.PropertyInput;
import org.bozdgn.propertyservice.dto.PropertyOutput;
import org.bozdgn.propertyservice.service.PropertyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping
    public List<PropertyOutput> getAccepted() {
        return propertyService.getAcceptedProperties();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public PropertyOutput get(@PathVariable Long id) {
        return propertyService.get(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PostMapping
    public PropertyOutput save(@RequestBody PropertyInput propertyInput) {
        return propertyService.save(propertyInput);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PutMapping
    public PropertyOutput update(@RequestBody PropertyInput propertyInput, HttpServletResponse response) {
        if (propertyInput.getId() != null) {
            return propertyService.save(propertyInput);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new PropertyOutput();
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        propertyService.delete(id);
    }

}
