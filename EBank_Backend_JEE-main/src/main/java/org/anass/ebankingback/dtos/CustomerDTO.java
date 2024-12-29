package org.anass.ebankingback.dtos;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.anass.ebankingback.entities.*;

import java.util.*;


@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;

}
