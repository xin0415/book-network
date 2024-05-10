package com.bookproject.book.book;

import com.bookproject.book.common.BaseEntity;
import com.bookproject.book.feedback.Feedback;
import com.bookproject.book.history.BookTransactionHistory;
import com.bookproject.book.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book extends BaseEntity {

    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")   // because in BookTransactionHistory uses book
    private List<BookTransactionHistory> histories;

    @Transient
    public double getRate(){
        if(feedbacks==null || feedbacks.isEmpty()){
            return 0.0;
        }
        var rate =this.feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        // 3.23 -->3.0 || 3.65-->4.0
        double roundedRate = Math.round(rate * 10.0) / 10.0;

        return roundedRate;
    }
}
