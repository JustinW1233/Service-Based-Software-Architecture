using AutoMapper;
public class AutoMapperProfile : Profile
{
    public AutoMapperProfile()
    {
        //CreateMap<UserDTO, User>();        
        CreateMap<OrderDTO, Order>();
        CreateMap<ProductDTO, Product>();
        //CreateMap<List<BookDTO>, List<Book>>();
    }
}