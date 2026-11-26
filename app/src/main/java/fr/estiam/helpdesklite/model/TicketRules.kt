
package fr.estiam.helpdesklite.model

object TicketRules {

    fun canTransition(from: Status, to: Status): Boolean {
        return when (from) {
            Status.OPEN -> to == Status.IN_PROGRESS
            Status.IN_PROGRESS -> to == Status.RESOLVED
            else -> false
        }
    }

    fun sortByPriority(tickets: List<SupportTicket>): List<SupportTicket> {
        return tickets.sortedBy { ticket ->
            when (ticket.priority) {
                Priority.HIGH -> 0
                Priority.MEDIUM -> 1
                Priority.LOW -> 2
            }
        }
    }
}
