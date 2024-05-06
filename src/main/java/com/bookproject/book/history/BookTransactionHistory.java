package com.bookproject.book.history;

import com.bookproject.book.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookTransactionHistory extends BaseEntity {
    // user relationship
    // book relationship

    private boolean returned;
    private boolean returnApproved;
}
