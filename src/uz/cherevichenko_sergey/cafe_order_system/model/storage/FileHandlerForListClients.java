package uz.cherevichenko_sergey.cafe_order_system.model.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uz.cherevichenko_sergey.cafe_order_system.model.interfaces.DataStore;
import uz.cherevichenko_sergey.cafe_order_system.model.client.ListClients;

import java.io.File;

public class FileHandlerForListClients implements DataStore<ListClients> {
    @Override
    public void save(ListClients clients){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // читаемый формат времени
        try {
            mapper.writeValue(new File("output_list_clients.json"), clients);
        } catch (Exception e) {
            System.out.println("Не возможно записать в файл");
        }
    }


    @Override
    public ListClients read() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            return mapper.readValue(new File("output_list_clients.json"), ListClients.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ListClients();
        }
    }


}
