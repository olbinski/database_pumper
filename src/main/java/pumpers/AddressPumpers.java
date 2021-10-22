package pumpers;


import entity.Address;

public class AddressPumpers extends AbstractPumper {


    public AddressPumpers() {
        super();
    }

    @Override
    protected void pump() {
        var address = Address.builder().addressId(1).build();

        session.save(address);

    }
}
