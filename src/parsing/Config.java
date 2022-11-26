package src.parsing;

import java.util.ArrayList;

public class Config {

    public int weatherChangeCount;

    public static class Data{

        public String type;
        public String name;
        public int longitude;
        public int latitude;
        public int height;

        public Data(String type, String name,int longitude,int latitude,int height)
        {
            this.type = type;
            this.name = name;
            this.longitude = longitude;
            this.latitude = latitude;
            this.height = height;
        }

    };

    public ArrayList<Data> data = new ArrayList<>();
    



}
