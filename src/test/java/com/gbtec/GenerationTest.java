package com.gbtec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GenerationTest {

    @Test
    void generation_should_create_two_dimensional_list_of_Cells_with_size_3x3() {
        // given
        int resultSize =3;
        // when
        Generation testGeneration = new Generation(3);
        // then
        for(int i=0;i<= testGeneration.getGeneration().size()-1; i++) {
            assertEquals(resultSize,testGeneration.getGeneration().get(i).size());
        }
      }

    @Test
    void generation_should_create_two_dimensional_list_of_Cells_with_size_20x20() {
        // given
        int resultSize =20;
        // when
        Generation testGeneration = new Generation(20);
        // then
        for(int i=0;i<= testGeneration.getGeneration().size()-1; i++) {
            assertEquals(resultSize,testGeneration.getGeneration().get(i).size());
        }
    }

    @Test
    void generation_should_create_a_matrix__of_dead_cells(){
        //given
        boolean shouldStatus = false;
        //when
        Generation generation = new Generation(11);
        //then
        for (int i = 0;i<=generation.getGeneration().size()-1; i++)
        {
            for (int j =0; j<=generation.getGeneration().size()-1;j++){
                assertEquals(shouldStatus,generation.getGeneration().get(i).get(j).getStatus());
            }
        }
    }
}
