package src.parsing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import src.exception.AvajEx;

// import src.Config.Data;

public class FileParser {

    public Config Parse(String fileName) {
        try {
            String line;
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            Config config = new Config();

            line = fileReader.readLine();
            config.weatherChangeCount = Integer.parseInt(line);

            if (config.weatherChangeCount < 0 ) {
                fileReader.close();
                throw new AvajEx("Invalid weather change count");
            }
            if(config.weatherChangeCount > 50) {
                fileReader.close();
                throw new AvajEx("Abuse pas frérot !");
            }

            while ((line = fileReader.readLine()) != null) {

                line = line.trim();
                String splitedLine[] = line.split(" ");
                if(splitedLine.length != 5) {
                    fileReader.close();
                    throw new AvajEx("Wrong number of arguments in simulation file");
                }

                String type = splitedLine[0];
                if(!type.equals("Helicopter") && !type.equals("JetPlane") && !type.equals("Baloon")) {
                    fileReader.close();
                    throw new AvajEx("Invalid aircraft type");
                }
                String name = splitedLine[1];
                int longitude =  Integer.parseInt(splitedLine[2]);
                int latitude =  Integer.parseInt(splitedLine[3]);
                int height =  Integer.parseInt(splitedLine[4]);
                for(int i = 3; i < splitedLine.length; i++) {
                   if(longitude < 0 || latitude < 0 || height < 0 ) {
                        fileReader.close();
                        throw new AvajEx("Invalid coordinates");  
                   }
                }
                
                config.data.add(new Config.Data(type, name, longitude, latitude, height));

            }
            fileReader.close();

            return config;
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Number Format invalid : " + e.toString());
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Can't open file : " + e.toString());
        } 
        catch (IOException e) 
        {
            System.out.println("IO error : " + e.toString());
        }
        catch (AvajEx e) 
        {
            System.out.println("Avaj error : " + e.toString());
            AvajEx.notice();
        }
        return null;
    }
}
