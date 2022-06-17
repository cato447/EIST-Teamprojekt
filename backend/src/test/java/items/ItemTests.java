<package items;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import whattocook.controller.ItemController;
import whattocook.models.Item;
import whattocook.models.Unit;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SpringBootTest(classes = whattocook.Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public final class ItemTests {



    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void postTest() {
        Item item = new Item(5L, "kartoffel", Unit.GRAMMS, 5000);


        assertThat(this.testRestTemplate.postForEntity("http://localhost:" + port + "/items", item, Item.class).getBody()).isEqualTo(item);
    }

    @Test
    public void getOneTest() {
        Item item = new Item(1L, "tortillias", Unit.GRAMMS, 5000);


       Long id= this.testRestTemplate.postForEntity("http://localhost:" + port + "/items", item, Item.class).getBody().getId();

        assertTrue(compNotId(this.testRestTemplate.getForObject("http://localhost:" + port + "/items/"+id, Item.class),item));

    }


    @Test
    public void getOneExeptionTest() {




        assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/items/"+900000L, Item.class).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    public void getallTest() {
        List<Item> items = new ArrayList();
        items.add( new Item(1L, "nachos", Unit.GRAMMS, 5000));
        items.add( new Item(2L, "wurst", Unit.GRAMMS, 5000));
        items.add( new Item(3L, "schinken", Unit.GRAMMS, 5000));
        items.add( new Item(4L, "brokkoli", Unit.GRAMMS, 5000));
        items.add( new Item(5L, "eiscreme", Unit.GRAMMS, 5000));


        for (Item item:items) {
           this.testRestTemplate.postForEntity("http://localhost:" + port + "/items", item, Item.class);
        }

        List<Map> getlist = this.testRestTemplate.getForObject("http://localhost:" + port + "/items",
                List.class);

        assertThat(mapListToName(getlist,"name")).containsAll(items.stream().map(Item::getName).toList());

    }

    @Test
    public void deleteTest() {
        Item item = new Item(16L, "elefantenfuß", Unit.GRAMMS, 5000);


        long id=this.testRestTemplate.postForEntity("http://localhost:" + port + "/items", item, Item.class).getBody().getId();
        this.testRestTemplate.delete("http://localhost:" + port + "/items/"+id);
        List<Map> getlist = this.testRestTemplate.getForObject("http://localhost:" + port + "/items",
                List.class);
        assertThat(mapListToName(getlist,"name")).doesNotContain(item.getName());
    }

    @Test
    public void deleteExceptionTest() throws URISyntaxException {
        ResponseEntity<Void> resp = testRestTemplate.exchange(new URI("http://localhost:" + port + "/items/"+70000), HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
       assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void putTest() {
        Item item = new Item(32L, "schokoküsse", Unit.GRAMMS, 5000);
        Item changed = new Item(32L, "schokoküsse", Unit.GRAMMS, 45634);


      long id=  this.testRestTemplate.postForEntity("http://localhost:" + port + "/items", item, Item.class).getBody().getId();
        this.testRestTemplate.put("http://localhost:" + port + "/items/"+id, changed);
        List<Map> getlist = this.testRestTemplate.getForObject("http://localhost:" + port + "/items",
                List.class);
        assertThat(mapListToName(getlist,"quantity")).contains(changed.getQuantity());
    }

    @Test
    public void putExceptionTest() throws URISyntaxException {
        Item changed = new Item(70000L, "gazellenkopf", Unit.GRAMMS, 80000);
        String resourceUrl =
                "http://localhost:" + port + "/items/"+700000;
        HttpEntity<Item> requestUpdate = new HttpEntity<>(changed);

        ResponseEntity<Void> resp = testRestTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate,Void.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private List mapListToName(List<Map> input, String param){
        return input.stream().map(t -> t.get(param)).toList();
    }

    private boolean compNotId(Item item1, Item item2){
        return item1.getName().equals(item2.getName())&&item1.getQuantity()==item2.getQuantity()&&item1.getUnit().equals(item2.getUnit());
    }
}
