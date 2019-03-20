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
    public StringBuilder subnetMaskBinary(){
        StringBuilder mask = new StringBuilder();
        for(int i = 0;i<32;i++){
            if(i<subnetMask){
                mask.append(1);
            }
            else{
                mask.append(0);
            }
            if((i+1)%8 == 0){
                mask.append(".");
            }
        }
        mask.delete(mask.length()-1,mask.length());
        return mask;
    }
    public StringBuilder subnetMaskDecimal(){
        StringBuilder mask2 = new StringBuilder();
       
        return mask2;
    }
}
