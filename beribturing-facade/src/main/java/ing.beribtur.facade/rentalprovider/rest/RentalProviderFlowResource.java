package ing.beribtur.facade.rentalprovider.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feature/rentalprovider")
@RequiredArgsConstructor
@PreAuthorize("hasRole('RENTALPROVIDER')")
public class RentalProviderFlowResource implements RentalProviderFlowFacade {
    //
}
