import java.io.IOException;

public class subnetCalculator {
    public static void main(String[] args) throws IOException {
        AddressIP addressIP = new AddressIP();
        if(args.length > 0)
        addressIP.addressValidation(args[0]);
        String myIpAddress = addressIP.getAddress();
        String networkAddress = addressIP.calculateNetworkAddress(myIpAddress);
        String broadcastAddress = addressIP.calculateBroadcastAddress();
        String firstHostAddress = addressIP.firstHostAddress();
        String lastHostAddress = addressIP.lastHostAddress();
        StringBuilder allInformations = new StringBuilder();
        allInformations.append("Adres IP: " + myIpAddress + "\n \n" + "Adres sieci: " +  "\n" + "Dziesiętnie: " + networkAddress + ", binarnie: " + addressIP.addressIpToBinary(networkAddress) + "\n \n"
        + "Maska sieci: " + "\n" + "Dziesiętnie: " + addressIP.subnetMaskDecimal() + ", binarnie: " + addressIP.subnetMaskBinary() + "\n \n"
        + "Adres broadcast: " + "\n" + "Dziesiętnie: " + broadcastAddress + ", binarnie: " + addressIP.addressIpToBinary(broadcastAddress) + "\n \n"
        + "Pierwszy adres hosta: " + "\n" + "Dziesiętnie: " + firstHostAddress + ", binarnie: " + addressIP.addressIpToBinary(firstHostAddress) + "\n \n"
        + "Ostatni adres hosta: " + "\n" + "Dziesiętnie: " + lastHostAddress + ", binarnie: " + addressIP.addressIpToBinary(lastHostAddress) + "\n \n"
        + "Maksymalna liczba hostów: " + addressIP.maxHostAmount() + "\n \n" + IsPrivate.isPrivate(myIpAddress) + "\n \n" + ClassOfAddress.whatClassOfAddress(myIpAddress));

        System.out.println(allInformations.toString());

        PingRequest.sendPingRequest();

        SaveToFile.saveToFileInformation(allInformations.toString());
    }
}

