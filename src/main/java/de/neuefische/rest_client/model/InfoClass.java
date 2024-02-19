package de.neuefische.rest_client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoClass {
            String count;
            String pages;
            String nextUrl;
            String prevUrl;
            List<RickAndMortyApiResponse> results;
}
