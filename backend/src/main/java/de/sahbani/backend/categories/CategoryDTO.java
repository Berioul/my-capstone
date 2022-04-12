package de.sahbani.backend.categories;

import de.sahbani.backend.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String name;
    private List<ItemDTO> items;
}
