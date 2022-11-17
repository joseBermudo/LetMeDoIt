package cat.copernic.letmedoit.General.model.provider

import cat.copernic.letmedoit.General.model.data.Opinions

class OpinionsProvider {
    companion object{
       fun getOpinions() : ArrayList<Opinions>{
           return arrayListOf(
               Opinions("1","Alex Cruceat","Creación Página Web", 5.0f,"Muy buen servicio."),
               Opinions("2","Hugo Barrera","Peluqueria", 3.5f,"Ok"),
               Opinions("3","Jose Bermudo","Creación Página Web", 1.0f,"Muy mal servicio"),
               Opinions("4","Alex Cruceat","Creación Página Web", 5.0f,"Muy buen servicio."),
               Opinions("5","Hugo Barrera","Peluqueria", 3.5f,"Ok"),
               Opinions("6","Hugo Barrera","Peluqueria", 3.5f, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sagittis tortor ut quam interdum dignissim. Nulla lobortis id tellus sed placerat. Curabitur varius metus magna, vitae ultrices orci gravida vel. Pellentesque vel erat vel lacus porta mattis vestibulum vel felis. In tellus arcu, pulvinar eget dolor id, ultrices egestas sem. Fusce finibus sapien vehicula nisl finibus, sed condimentum orci tempor. Cras viverra maximus massa, nec posuere velit commodo eget. Etiam in lacus venenatis, porttitor metus ut, pellentesque dolor. Donec et faucibus turpis. Duis eu dapibus turpis. Proin ultrices, neque lacinia hendrerit posuere, augue ipsum tincidunt nisi, at bibendum dui ipsum vitae metus."),
               Opinions("8","Jose Bermudo","Creación Página Web", 1.0f,"Muy mal servicio"),
           )
       }
    }
}