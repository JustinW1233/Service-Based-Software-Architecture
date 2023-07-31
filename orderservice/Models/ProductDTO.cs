using System.ComponentModel.DataAnnotations;

public class ProductDTO{
    public Guid ProductGuid {get; set;}
    public string productName {get;set;}
    public string description {get;set;}
    public string rating {get;set;}
    public int price {get;set;}
}