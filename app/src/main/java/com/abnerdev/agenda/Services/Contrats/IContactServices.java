package com.abnerdev.agenda.Services.Contrats;

import com.abnerdev.agenda.Model.Contact;

public interface IContactServices {


    public boolean Create(String Id_user, Contact contact);

    public boolean Update(String Id_user,Contact contact );

    public Contact FindById(String Id_user,String Id_contact);
}
