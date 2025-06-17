package ing.beribtur.aggregate.maintenance.entity.vo;


/**
 * Represents the status of a maintenance request.
 */
public enum MaintenanceStatus {
    REQUESTED,      // Maintenance has been requested but not yet reviewed
    UNDER_REVIEW,   // Maintenance request is being reviewed
    APPROVED,       // Maintenance request has been approved and will be performed
    IN_PROGRESS,    // Maintenance is currently being performed
    COMPLETED,      // Maintenance has been completed
    REJECTED,       // Maintenance request has been rejected
    CANCELLED       // Maintenance request has been cancelled by the requester
}
