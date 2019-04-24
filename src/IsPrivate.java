public class IsPrivate {
    public static String isPrivate(String addressTo){
        int addressToInt = AddressIP.addressToInt(addressTo);
        String resultIsPrivate;
        if(addressToInt >= AddressIP.addressToInt("10.0.0.0") &&  addressToInt <= AddressIP.addressToInt("10.255.255.255")){
            resultIsPrivate = "Podany adres jest adresem prywatnym.";
        }
        else if(addressToInt >= AddressIP.addressToInt("172.16.0.0") &&  addressToInt <= AddressIP.addressToInt("172.31.255.255")){
            resultIsPrivate = "Podany adres jest adresem prywatnym.";
        }
        else if(addressToInt >= AddressIP.addressToInt("192.168.0.0") &&  addressToInt <= AddressIP.addressToInt("192.168.255.255")){
            resultIsPrivate = "Podany adres jest adresem prywatnym.";
        }
        else{
            resultIsPrivate = "Podany adres jest adresem publicznym.";
        }
        return resultIsPrivate;
    }
}
