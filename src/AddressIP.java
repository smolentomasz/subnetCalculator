import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;


public class AddressIP {
    private InetAddress localHost;
    private static InetAddress hostAddressToPing;
    private String address;
    private int subnetMask;
    private int network, broadcast;

    public static InetAddress getHostAddressToPing() {
        return hostAddressToPing;
    }

    public String getAddress() {
        return address;
    }

    public void addressValidation(String commandLine) throws UnknownHostException, SocketException {
        if(!verifyAddress(commandLine)){
            System.out.println("Address is not valid! Script will get PC IP address." + System.getProperty("line.separator"));
            localHost = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
            address = localHost.getHostAddress();
            hostAddressToPing = InetAddress.getByName(address);
            subnetMask = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
        }
        else{
            System.out.println("Address is valid!" + System.getProperty("line.separator"));
            String[] parts = commandLine.split("/");
            address = parts[0];
            hostAddressToPing = InetAddress.getByName(parts[0]);
            subnetMask = Integer.parseInt(parts[1]);
        }
    }
    private boolean verifyAddress(String address){
        address = address.trim();
        if(address == null || address.equals(""))
            return false;

        if(!address.matches("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])(\\/)([1-9]|1\\d|2\\d|3[0-2])$"))
            return false;
        return true;
    }
    public String subnetMaskBinary(){
        String maskBinary = new String();
        for(int i = 0;i<32;i++){
            if(i<subnetMask){
                maskBinary += 1;
            }
            else{
                maskBinary += 0;
            }
            if((i+1)%8 == 0 && i != 31){
                maskBinary += ".";
            }
        }
        return maskBinary;
    }
    public String subnetMaskDecimal(){
        String maskDecimal = new String();
        String[] decimalParts = subnetMaskBinary().split("\\.");
        for(int i = 0;i<decimalParts.length;i++){
            maskDecimal += Integer.parseInt(decimalParts[i],2);
            if(i%1 == 0 && i != 3){
                maskDecimal += ".";
            }
        }
        return maskDecimal;
    }
    public String addressIpToBinary(String addresTo){
        String addressIpBinary = new String();
        String[] addresBinaryParts = addresTo.split("\\.");
        for(int i = 0;i<addresBinaryParts.length;i++){
            int parsedDecimal = Integer.toBinaryString(Integer.parseInt(addresBinaryParts[i])).length();
            while (parsedDecimal != 8){
                addressIpBinary += 0;
                parsedDecimal += 1;
            }
            addressIpBinary += Integer.toBinaryString(Integer.parseInt(addresBinaryParts[i]));
            if(i%1 == 0 && i != 3){
                addressIpBinary += ".";
            }
        }
        return addressIpBinary;
    }
    public static int addressToInt(String addressTo){
        int addressInt = 0;
        String[] addressParts = addressTo.split("\\.");
        for (int i = 3; i >= 0; i--) {
            long ip = Long.parseLong(addressParts[3 - i]);
            addressInt |= ip << (i * 8);
        }
        return addressInt;
    }
    public String intToAddress(int addressTo){
        StringBuilder addressIP = new StringBuilder(15);
        for (int i = 0; i < 4; i++) {
            addressIP.insert(0,(addressTo & 0xff));
            if (i < 3) {
                addressIP.insert(0,'.');
            }
            addressTo = addressTo >> 8;
        }
        return addressIP.toString();
    }
    public String calculateNetworkAddress(String addressTo){
        network = (addressToInt(addressTo) & addressToInt(subnetMaskDecimal()));
        return intToAddress(network);
    }
    public String calculateBroadcastAddress(){
        broadcast = (network | ~(addressToInt(subnetMaskDecimal())));
        return intToAddress(broadcast);
    }
    public String firstHostAddress(){
        return intToAddress(network | 1);
    }
    public String lastHostAddress(){
        return intToAddress(broadcast ^ 1);
    }
    public int maxHostAmount(){
        int maxHostNumber;
        maxHostNumber = (broadcast ^ 1) - (network);
        return maxHostNumber;
    }
}
