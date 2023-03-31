public class DistanceUtils {

    public static double distanceTwoPoints(double lat1, double lon1,
                                           double lat2, double lon2) {
        return haversine(lat1, lon1, lat2, lon2);
    }

    public static double distanceThreePoints(double lat1, double lon1, double lat2, double lon2,
                                             double lat3, double lon3) {
        return haversine(lat1, lon1, lat2, lon2) + haversine(lat2, lon2, lat3, lon3);
    }


    static double haversine(double lat1, double lon1,
                            double lat2, double lon2) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }


}
