import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class AddressIP {
    private InetAddress localHost;

    public String getAddress() {
        return address;
    }

    public int getSubnetMask() {
        return subnetMask;
    }

    private String address;
    private int subnetMask;
    public void addressValidation(String commandLine) throws UnknownHostException, SocketException {
        if(verifyAddress(commandLine) == false){
            System.out.println("Address is not valid! Script will get PC IP address.");
            localHost = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
            address = localHost.getHostAddress();
            subnetMask = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
        }
        else{
            System.out.println("Address is valid!");
            String[] parts = commandLine.split("/");
            address = parts[0];
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
    public String addressIpToBinary(){
        String addressIpBinary = new String();
        String[] addresBinaryParts = address.split("\\.");
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

}
