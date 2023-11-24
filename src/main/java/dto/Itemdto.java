package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Itemdto {
    private String code;
    private String description;
    private double unitprize;
    private int qty;
}
