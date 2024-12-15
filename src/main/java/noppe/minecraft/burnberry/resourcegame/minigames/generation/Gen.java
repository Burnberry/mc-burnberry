package noppe.minecraft.burnberry.resourcegame.minigames.generation;

import noppe.minecraft.burnberry.helpers.M;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Gen {
    public static List<Point> directions = getDirections();


    public static List<Point> generateNodes(int w, int h, int n, double k, double dk, double dpk){
        List<List<Point>> area = new ArrayList<>();
        for (int i = 0; i < h; i++){
            List row = new ArrayList<>();
            area.add(row);
            for (int j = 0; j < w; j++){
                row.add(new Point(j, i, -1));
            }
        }

        for (int i = 0; i<n; i++){
            spawnNode(area, getRandomPoint(area), k, dk, dpk);
        }

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                points.add(area.get(i).get(j));
            }
        }

        return points;
    }

    public static Point getRandomPoint(List<List<Point>> area){
        int x = ThreadLocalRandom.current().nextInt(0, area.get(0).size());
        int y = ThreadLocalRandom.current().nextInt(0, area.size());
        return area.get(y).get(x);
    }

    public static void spawnNode(List<List<Point>> area, Point point, double k, double dk, double dpk){
        Set<Point> seen = new HashSet();
        seen.add(point);
        Set<Point> points = new HashSet();
        points.add(point);
        point.k = k;

        while (!points.isEmpty()){
            Set<Point> pointsNew = new HashSet();
            for (Point point1: points){
                seen.add(point1);
                k = point1.k;
                for (Point dir: directions){
                    double kNew = k - dk - dpk*ThreadLocalRandom.current().nextDouble();
                    if (kNew < 0){
                        continue;
                    }
                    int x = point1.x + dir.x;
                    int y = point1.y + dir.y;
                    if (!insideArea(area, x, y)){
                        continue;
                    }
                    Point pointNew = area.get(y).get(x);
                    if (seen.contains(pointNew)){
                        continue;
                    }
                    pointNew.k = Math.max(pointNew.k, kNew);
                    pointsNew.add(pointNew);
                }
            }
            points = pointsNew;
        }
    }

    public static boolean insideArea(List<List<Point>> area, int x, int y){
        return (0 < x) && (x < area.get(0).size()) && (0 < y) && (y < area.size());
    }

    public static List<Point> getDirections(){
        List<Point> directions = new ArrayList<>();
        directions.add(new Point(1, 0, 1));
        directions.add(new Point(-1, 0, 1));
        directions.add(new Point(0, 1, 1));
        directions.add(new Point(0, -1, 1));
        return directions;
    }
}
