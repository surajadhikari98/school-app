package io.reactivestax.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EnrollmentId implements Serializable {
    private Long studentId;
    private Long courseId;
}
