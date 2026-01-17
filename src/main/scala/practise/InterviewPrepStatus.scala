package practise

sealed trait InterviewPrepStatus
case object Cleared extends InterviewPrepStatus
case object NotCleared extends InterviewPrepStatus

def handleStatus(status: InterviewPrepStatus): String = {
  status match {
    case Cleared => "Cleared"
    case NotCleared => "Nor cleared"
  }
}
