package com.data;

import java.util.Vector;

public class Client {

    // Static
    // All streets in list
    public static Vector<String> streets = new Vector<String>();
    public static Vector<String> locations = new Vector<String>();
    public static Vector<String> types = new Vector<String>();
    public static Vector<String> phones = new Vector<String>();
    public static Vector<String> postallCodes = new Vector<String>();
    public static Vector<String> id_s = new Vector<String>();
    //  List with Clients
    public static Vector<Client> all_clients_list = new Vector<Client>();
    // Counter Object (Clients)
    public static int id = 0;

    // Base imformations
    public String street = "    --    ";
    public String location = "    --    ";
    public String type = "    --    ";
    public String phone = "    --    ";
    public String postallCode = "    --    ";
    public String my_id = "    --    ";
    // Characteristics list
    public Vector<String> characteristics_names = new Vector<String>();
    // Damage list
    public Vector<String> damage_dates = new Vector<String>();
    public Vector<String> damage_worker = new Vector<String>();
    public Vector<String> damage_damage_type = new Vector<String>();
    public Vector<String> damage_fix_type = new Vector<String>();
    // Maintence list
    public Vector<String> maintence_dates = new Vector<String>();
    public Vector<String> maintence_worker = new Vector<String>();
    public Vector<String> maintence_comments = new Vector<String>();
    // Repairs list
    public Vector<String> repairs_dates = new Vector<String>();
    public Vector<String> repairs_worker = new Vector<String>();
    public Vector<String> repairs_repair_type = new Vector<String>();
    // Battaries list
    public Vector<String> battaries_dates = new Vector<String>();
    public Vector<String> battaries_type1 = new Vector<String>();
    public Vector<String> battaries_type2 = new Vector<String>();
    public Vector<String> battaries_type3 = new Vector<String>();
    // DocNum list
    public Vector<String> DocNum_dates = new Vector<String>();
    public Vector<String> DocNum_docnum = new Vector<String>();
    public Vector<String> DocNum_Work = new Vector<String>();
    public Vector<String> DocNum_Price = new Vector<String>();
    public Vector<String> DocNum_Repayment = new Vector<String>();

    //Constractor 1
    public Client (String street) {
        id++;
        this.street = street;
    }
    // Constractors 2
    public Client () {
        id++;
        this.my_id = String.valueOf(id-1);
        //all_clients_list.add(this);
    }

    // Remove id of deleted object
    public static void removeID() {
        id--;
    }

    // Clients Clear
    public static void clear() {
        id = 0;
        streets.clear();
        locations.clear();
        types.clear();
        phones.clear();
        postallCodes.clear();
        id_s.clear();
        for (Client client : all_clients_list) {
            client.street = null;
            client.location = null;
            client.type = null;
            client.phone = null;
            client.postallCode = null;
            client.my_id = null;
            client.characteristics_names.clear();
            client.damage_dates.clear();
            client.damage_worker.clear();
            client.damage_damage_type.clear();
            client.damage_fix_type.clear();
            client.maintence_dates.clear();
            client.maintence_worker.clear();
            client.maintence_comments.clear();
            client.repairs_dates.clear();
            client.repairs_worker.clear();
            client.repairs_repair_type.clear();
            client.battaries_dates.clear();
            client.battaries_type1.clear();
            client.battaries_type2.clear();
            client.battaries_type3.clear();
        }
        all_clients_list.clear();
    }

}