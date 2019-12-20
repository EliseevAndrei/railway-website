package com.eliseev.app.dto;

import com.eliseev.app.models.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderDTO {

    private Ticket ticket;
    private Long trainDateId;
    private Long startRoutePieceId;
    private Long endRoutePieceId;
    private int startRoutePieceSerialNumber;
    private int endRoutePieceSerialNumber;
}
