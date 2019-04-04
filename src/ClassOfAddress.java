public class ClassOfAddress {
    public static String whatClassOfAddress(String addressTo){
        int addressToInt = AddressIP.addressToInt(addressTo);
        String resultWhatClass;
        if(addressToInt >= AddressIP.addressToInt("0.0.0.0") &&  addressToInt <= AddressIP.addressToInt("127.255.255.255")){
            resultWhatClass = "Podany adres należy do klasy A.";
        }
        else if(addressToInt >= AddressIP.addressToInt("128.0.0.0") &&  addressToInt <= AddressIP.addressToInt("191.255.255.255")){
            resultWhatClass = "Podany adres należy do klasy B.";
        }
        else if(addressToInt >= AddressIP.addressToInt("192.0.0.0") &&  addressToInt <= AddressIP.addressToInt("223.255.255.255")){
            resultWhatClass = "Podany adres należy do klasy C.";
        }
        else if(addressToInt >= AddressIP.addressToInt("224.0.0.0") &&  addressToInt <= AddressIP.addressToInt("239.255.255.255")){
            resultWhatClass = "Podany adres należy do klasy D.";
        }
        else{
            resultWhatClass = "Podany adres należy do klasy E.";
        }
        return resultWhatClass;
    }
}
