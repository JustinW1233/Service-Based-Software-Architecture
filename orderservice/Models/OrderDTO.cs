using System.ComponentModel.DataAnnotations;

public class OrderDTO
{
    public Guid OrderGuid { get; set; }
    public Guid BasketGuid { get; set; }
    public List<ProductDTO>? Products { get; set; }
}