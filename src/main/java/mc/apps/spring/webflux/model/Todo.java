package mc.apps.spring.webflux.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

//@Entity
@Document(collection = "_todos")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Todo  { //implements Serializable

    @Id
    private String id;

    private String title;
    private boolean done = false;

    public Todo(String title) {
        this.title = title;
    }
    public Todo(String title, boolean done) {
        this.title = title;
        this.done = done;
    }
}
