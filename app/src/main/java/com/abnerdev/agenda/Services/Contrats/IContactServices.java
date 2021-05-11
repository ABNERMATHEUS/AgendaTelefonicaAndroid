package com.abnerdev.agenda.Services.Contrats;

import com.abnerdev.agenda.Model.Contact;

public interface IContactServices {


    public boolean Create(Contact contact);

    public boolean Update(Contact contact );

    public Contact FindById(String Id_contact);

    public int CountContact();
    public void Delete(String id_contact);
}
