package site.gabriellima.acmeapi.service.dto;

import site.gabriellima.acmeapi.domain.enums.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class StatusDTO implements Serializable {

  @NotNull
  private Status status;
  private String note;

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
