package uz.cherevichenko_sergey.cafe_order_system.model.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uz.cherevichenko_sergey.cafe_order_system.model.interfaces.DataStore;
import uz.cherevichenko_sergey.cafe_order_system.model.dish.ListDishes;

import java.io.File;

public class FileHandlerForListDishes implements DataStore<ListDishes> {
    @Override
    public void save(ListDishes dishes){

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(new File("output_list_dishes.json"),dishes);
        }
        catch (Exception e){
            System.out.println("Не возможно записать в файл");
        }
    }

    @Override
    public ListDishes read()  {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            return mapper.readValue(new File("output_list_dishes.json"),ListDishes.class);

        } catch (Exception e){
            e.printStackTrace();
            return new ListDishes();

        }
    }
}
