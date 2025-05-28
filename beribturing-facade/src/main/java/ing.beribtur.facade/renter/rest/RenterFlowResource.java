package ing.beribtur.facade.renter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feature/rentalprovider")
@RequiredArgsConstructor
@PreAuthorize("hasRole('RENTER')")
public class RenterFlowResource implements RenterFlowFacade {
    //
}
