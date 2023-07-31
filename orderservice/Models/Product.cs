using System.ComponentModel.DataAnnotations;

public class Product{

    [Key]
    public Guid ProductGuid {get; set;}
    
    [Required]
    public string productName {get;set;}

    [Required]
    public string description {get;set;}

    [Required]
    public string rating {get;set;}

    [Required]
    public int price {get;set;}

    [Required]
    public Order Order {get;set;}

    [Required] 
    public Guid OrderGuid {get;set;}
}