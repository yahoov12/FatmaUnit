import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
public class Phatma_Unit {

    // רשימות לכל משאב אפשרי: אירועים, לוחמי אש, מפקדים, מטוסים ומשאיות
    private ArrayList<Fire_Event> List_Of_Events; 
    private ArrayList<Fire_Fighter> List_Of_Fighters;
    private ArrayList<Fire_Commander> List_Of_Commanders;
    private ArrayList<Fire_Plane> List_Of_Planes;
    private ArrayList<Fire_Truck> List_Of_Trucks;
    
    // בנאי שמאתחל את כל הרשימות
    public Phatma_Unit(String FireFighters, String Vehicles) {
        this.List_Of_Events = new ArrayList<>();
        this.List_Of_Fighters = new ArrayList<>();
        this.List_Of_Commanders = new ArrayList<>();
        this.List_Of_Planes = new ArrayList<>();
        this.List_Of_Trucks = new ArrayList<>();

        try {
            // קריאת נתונים על לוחמי אש מקובץ
            BufferedReader reader1 = new BufferedReader(new FileReader(FireFighters));
            reader1.readLine(); // דילוג על כותרת הקובץ
            String line;
            while ((line = reader1.readLine()) != null) {
                String[] row = line.split("\t"); // פיצול השורה לטבלה
                if (row.length == 5) { // אם יש 5 עמודות, זהו מפקד
                        Fire_Commander c = new Fire_Commander(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2]),
                                Double.parseDouble(row[3]), Integer.parseInt(row[4]));
                        addCommander(c); // הוספת המפקד לרשימה
                   
                } else { // אם אין 5 עמודות, זהו לוחם
                    Fire_Fighter f = new Fire_Fighter(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2]),
                            Double.parseDouble(row[3]));
                    addFighter(f);
                }
            }
            reader1.close();
        } catch (IOException e) {
            throw new RuntimeException(e); // טיפול בשגיאות בקריאת הקובץ
        }

        try {
            // קריאת נתונים על רכבים מקובץ
            BufferedReader reader2 = new BufferedReader(new FileReader(Vehicles));
            reader2.readLine(); // דילוג על כותרת הקובץ
            String line;
            while ((line = reader2.readLine()) != null) {
                String[] row = line.split("\t"); // פיצול השורה לטבלה
                if (row[0].equals("P")) { // אם העמודה הראשונה היא "P", זהו מטוס
                    
                        Fire_Plane p = new Fire_Plane(Integer.parseInt(row[1]), Integer.parseInt(row[2]),
                                Double.parseDouble(row[3]), Double.parseDouble(row[4]), Integer.parseInt(row[5]));
                        addPlane(p);
                } else { // אחרת זהו משאית
                    Fire_Truck t = new Fire_Truck(Integer.parseInt(row[1]), Integer.parseInt(row[2]),
                            Double.parseDouble(row[3]), Double.parseDouble(row[4]), Integer.parseInt(row[5]));
                    addTruck(t);
                }
            }
            reader2.close();
        } catch (IOException e) {
            throw new RuntimeException(e); // טיפול בשגיאות בקריאת הקובץ
        }
    }


    // פונקציה להוספת לוחם אש לרשימת הלוחמים
    public boolean addFighter(Fire_Fighter f) {
        this.List_Of_Fighters.add(f); 
        return true;    
    }

    // פונקציה להוספת מפקד לרשימת המפקדים
    public boolean addCommander(Fire_Commander c) {
        this.List_Of_Commanders.add(c);
        return true;
    }

    // פונקציה להוספת מטוס כיבוי לרשימה, עם בדיקה לגובה מינימלי חוקי
    public boolean addPlane(Fire_Plane p) {
        if (p.getMinHeight() > 50) // בדיקה אם גובה המינימום גבוה מדי
            return false; 
        this.List_Of_Planes.add(p);
        return true;
    }

    // פונקציה להוספת משאית כיבוי לרשימה
    public boolean addTruck(Fire_Truck t) {
        this.List_Of_Trucks.add(t);
        return true;
    }

   
    // חישוב ממוצע הניסיון של לוחמים ורכבים
    public static double averageExperience(ArrayList<Object> o) {
        double sumExpirience = 0;
        for (int i = 0; i < o.size(); i++) {
            if (o.get(i) instanceof Fire_Fighter)
                sumExpirience += ((Fire_Fighter) o.get(i)).yearsExperience; // חישוב ניסיון לוחם
            if (o.get(i) instanceof Fire_Vehicle)
                sumExpirience += ((Fire_Vehicle) o.get(i)).yearsExperience; // חישוב ניסיון רכב
        }
        return sumExpirience / o.size();
    }

    // חישוב ממוצע גיל הלוחמים
    public static double averageAge(ArrayList<Object> o) {
        double sumAge = 0;
        for (int i = 0; i < o.size(); i++) {
            if (o.get(i) instanceof Fire_Fighter)
                sumAge += ((Fire_Fighter) o.get(i)).Age; // צבירת גיל
        }
        return sumAge / o.size();
    }

    // מציאת האובייקט עם הערך המקסימלי ברשימה
    public static Comparable getMax(ArrayList<? extends Comparable> c) {
        Comparable max = c.get(0);
        for (int i = 1; i < c.size(); i++) {
            if (((Comparable) c.get(i)).compareTo(max) > 0)
                max = (Comparable) c.get(i); // עדכון ערך מקסימלי
        }
        return max;
    }

    // מציאת רכבים זמינים
    public static <T extends Fire_Vehicle> ArrayList<T> getAvailableVehicles(ArrayList<T> list) {
        ArrayList<T> availableVehicles = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T vehicle = list.get(i); // בדיקת רכב
            if (vehicle.Avalible) { 
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    // מציאת לוחמים זמינים
    public static <T extends Fire_Fighter> ArrayList<T> getAvailableFighters(ArrayList<T> list) {
        ArrayList<T> availableFighters = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            T fighter = list.get(i); // בדיקת לוחם
            if (fighter.Avalible) {
                availableFighters.add(fighter);
            }
        }
        return availableFighters;
    }

    // ניהול אירוע כיבוי אש
public boolean eventHandeling(int level, String location) {
    boolean flag = false;
    int requireWaterAmount = level * 10000; // חישוב כמות מים נדרשת לפי דרגת האירוע
    Fire_Event event = new Fire_Event(level, location); // יצירת אירוע חדש

    // יצירת רשימות של משאבים זמינים
    ArrayList<Fire_Fighter> List_Of_Avallable_Fighters = getAvailableFighters(List_Of_Fighters);
    ArrayList<Fire_Commander> List_Of_Avallable_Commanders = getAvailableFighters(List_Of_Commanders);
    ArrayList<Fire_Truck> List_Of_Avallable_Trucks = getAvailableVehicles(List_Of_Trucks);
    ArrayList<Fire_Plane> List_Of_Avallable_Planes = getAvailableVehicles(List_Of_Planes);

    if (level <= 5) { // אירוע בדרגה נמוכה, משתמשים בעיקר במשאיות
        int numOfFighters;
        while (requireWaterAmount > 0) {
            if (List_Of_Avallable_Trucks.size() == 0)
                return false; // אין מספיק משאבים
            Fire_Truck t = (Fire_Truck) getMax(List_Of_Avallable_Trucks); // משאית עם הערך הגבוה ביותר
            if (List_Of_Avallable_Commanders.size() > 0) {
                Fire_Commander c = (Fire_Commander) getMax(List_Of_Avallable_Commanders); // מפקד עם הערך הגבוה ביותר
                t.addCommander(c); 
                List_Of_Avallable_Commanders.remove(c);
            } else return false; // אם אין מפקדים זמינים
            numOfFighters = t.getNumFigthers() - 1; // מקצים לוחמים למשאית
            while (numOfFighters > 0) {
                if (List_Of_Avallable_Fighters.size() == 0)
                    return false; // אין מספיק לוחמים
                Fire_Fighter f = (Fire_Fighter) getMax(List_Of_Avallable_Fighters); // לוחם עם הערך הגבוה ביותר
                t.addFighter(f);
                numOfFighters--;
                List_Of_Avallable_Fighters.remove(f);
            }
            if (numOfFighters == 0) {
                event.addVehicle(t); // הוספת המשאית לאירוע
                requireWaterAmount -= t.getwaterAmount(); // הפחתת המים שסופקו
            }
            List_Of_Avallable_Trucks.remove(t);
            t.Avalible = false; // סימון המשאית כלא זמינה
        }
        flag = requireWaterAmount <= 0; // בדיקה אם הדרישות מולאו
    } else if (level > 5) { // אירוע בדרגה גבוהה, שימוש גם במטוסים
        int numOfFighters;
        int numberOfPlanes = 0;
        while (requireWaterAmount > 0) {
            if (numberOfPlanes < 2 && List_Of_Avallable_Planes.size() != 0) {
                Fire_Plane p = (Fire_Plane) getMax(List_Of_Avallable_Planes); // מטוס עם הערך הגבוה ביותר
                if (List_Of_Avallable_Commanders.size() > 0) {
                    Fire_Commander c = (Fire_Commander) getMax(List_Of_Avallable_Commanders);
                    p.addCommander(c);
                    List_Of_Avallable_Commanders.remove(c);
                } else return false;
                if (List_Of_Avallable_Fighters.size() > 0) {
                    Fire_Fighter f = (Fire_Fighter) getMax(List_Of_Avallable_Fighters);
                    p.addFighter(f);
                    List_Of_Avallable_Fighters.remove(f);
                } else return false;
                event.addVehicle(p);
                List_Of_Avallable_Planes.remove(p);
                requireWaterAmount -= p.getwaterAmount();
                p.Avalible = false;
                numberOfPlanes++;
            } else { // מעבר למשאיות אם אין עוד מטוסים
                if (List_Of_Avallable_Trucks.size() == 0)
                    return false;
                Fire_Truck t = (Fire_Truck) getMax(List_Of_Avallable_Trucks);
                if (List_Of_Avallable_Commanders.size() > 0) {
                    Fire_Commander c = (Fire_Commander) getMax(List_Of_Avallable_Commanders);
                    t.addCommander(c);
                    List_Of_Avallable_Commanders.remove(c);
                } else return false;
                numOfFighters = t.getNumFigthers() - 1;
                while (numOfFighters > 0) {
                    if (List_Of_Avallable_Fighters.size() == 0)
                        return false;
                    Fire_Fighter f = (Fire_Fighter) getMax(List_Of_Avallable_Fighters);
                    t.addFighter(f);
                    numOfFighters--;
                    List_Of_Avallable_Fighters.remove(f);
                }
                if (numOfFighters == 0) {
                    event.addVehicle(t);
                    requireWaterAmount -= t.getwaterAmount();
                }
                List_Of_Avallable_Trucks.remove(t);
                t.Avalible = false;
            }
        }
        flag = requireWaterAmount <= 0;
    }
    if (flag) {
        List_Of_Events.add(event); // הוספת האירוע לרשימת האירועים
        giveEventSalary(event); // הענקת משכורות על האירוע
    } else System.out.println("Not enough resources to handle the event at location: " + location);
    return flag;
}
    

    // חישוב ההוצאות הכוללות עבור רשימת אובייקטים
public static double totalExpences(ArrayList<Object> o) {
    double totalexpences = 0;
    for (int i = 0; i < o.size(); i++) {
        if (o.get(i) instanceof Fire_Vehicle) 
            totalexpences += ((Fire_Vehicle) o.get(i)).eventCost; // הוספת עלות רכב
        else if (o.get(i) instanceof Fire_Fighter) 
            totalexpences += ((Fire_Fighter) o.get(i)).Salary; // הוספת שכר לוחם
    }
    return totalexpences; // החזרת סכום ההוצאות
}

   // הענקת משכורות ללוחמים ומפקדים שהשתתפו באירוע
public void giveEventSalary(Fire_Event event) {
    for (int i = 0; i < event.List_Of_Assigned_Vehicle.size(); i++) {
        for (int j = 0; j < event.List_Of_Assigned_Vehicle.get(i).List_Of_Figthers.size(); j++) {
            if ((event.List_Of_Assigned_Vehicle.get(i).List_Of_Figthers.get(j) instanceof Fire_Commander))
                event.List_Of_Assigned_Vehicle.get(i).List_Of_Figthers.get(j).setSalary(300); // שכר מפקד
            else event.List_Of_Assigned_Vehicle.get(i).List_Of_Figthers.get(j).setSalary(200); // שכר לוחם
        }
    }
}

// סגירת אירוע והחזרת המשאבים למצב זמין
public boolean closeEvent(String location) {
    Fire_Event e = new Fire_Event(0, ""); // יצירת אירוע ריק

    boolean existingEvent = false;
    // חיפוש האירוע ברשימת האירועים
    for (int i = 0; i < List_Of_Events.size(); i++) {
        if (List_Of_Events.get(i).Location.equals(location)) { 
            e = List_Of_Events.get(i); // מציאת האירוע לפי המיקום
            existingEvent = true; // מצביע שהאירוע קיים
        }
    }
    if (!existingEvent) // אם לא נמצא האירוע, מחזירים false
        return false;

    double eventTotalExpences = totalExpences(new ArrayList<Object>(e.List_Of_Assigned_Vehicle)); 
    double averageFightersExperience = 0; // ממוצע ניסיון של לוחמים
    double averageVehicleExperience = averageExperience(new ArrayList<Object>(e.List_Of_Assigned_Vehicle)); // ממוצע ניסיון של רכבים
    int NוumberOfAssignedFighters = 0; // מספר לוחמים שהוקצו לאירוע
    int numberOfAssignedVehicles = e.List_Of_Assigned_Vehicle.size(); // מספר כלי רכב שהוקצו לאירוע

    // החזרת המשאבים לזמינות, שחרור כלי הרכב והלוחמים
    while (e.List_Of_Assigned_Vehicle.size() > 0) {
        eventTotalExpences += totalExpences(new ArrayList<Object>(e.List_Of_Assigned_Vehicle.get(0).List_Of_Figthers)); // הוספת הוצאות לוחמים
        averageFightersExperience += averageExperience(new ArrayList<Object>(e.List_Of_Assigned_Vehicle.get(0).List_Of_Figthers)); // צבירת ניסיון לוחמים
        while (e.List_Of_Assigned_Vehicle.get(0).List_Of_Figthers.size() > 0) {
            NוumberOfAssignedFighters++; // ספירת לוחמים שהוקצו לאירוע
            e.List_Of_Assigned_Vehicle.get(0).List_Of_Figthers.get(0).Avalible = true; // החזרת לוחם לזמינות
            e.List_Of_Assigned_Vehicle.get(0).List_Of_Figthers.remove(0); // הסרת הלוחם מהרשימה
        }
        e.List_Of_Assigned_Vehicle.get(0).Avalible = true; // החזרת כלי הרכב לזמינות
        e.List_Of_Assigned_Vehicle.remove(0); // הסרת כלי הרכב מהרשימה
    }

    // הדפסת פרטי הסיכום של האירוע
    System.out.println("Fire event ended");
    System.out.println("Location:" + location);
    System.out.println("Level:" + e.getLevel());
    System.out.println("Number of fighter's in the event: " + NוumberOfAssignedFighters);
    System.out.println("Fire fighter's average years of experience: " + (averageFightersExperience / numberOfAssignedVehicles));
    System.out.println("Fire vehicle's average years of experience: " + averageVehicleExperience);
    System.out.println("Event cost: " + eventTotalExpences);

    List_Of_Events.remove(e); // הסרת האירוע מהרשימה
    return true; // החזרת true, מכיוון שהאירוע נסגר בהצלחה
}


    // פונקציית main להרצת התוכנית
    public static void main(String[] args) {
        Phatma_Unit yuval = new Phatma_Unit("FireFighters.txt", "Vehicles.txt");
        yuval.eventHandeling(8, "Bash");
        yuval.eventHandeling(4, "holon");
        yuval.closeEvent("Bash");
        yuval.closeEvent("holon");
    }
}

