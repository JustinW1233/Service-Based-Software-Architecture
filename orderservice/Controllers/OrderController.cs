using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using AutoMapper;

[ApiController]
[Route("[controller]")]
public class OrderController : ControllerBase
{
    private readonly OrderServiceDBContext _db;
    private readonly IMapper _mapper;

    public OrderController(OrderServiceDBContext db, IMapper mapper)
    {
        _db = db;
        _mapper = mapper;
    }

    [HttpGet]
    [Route("Test1")]
    public async Task<ActionResult<List<Order>>> Test1()
    {
        return Ok("hi there");
    }

    [HttpGet]
    public async Task<ActionResult<List<Order>>> Get()
    {
        try
        {
            //var Data = await _orderDBDapper.GetAll();
            List<Order> orders = await _db.Orders.ToListAsync();
            return Ok(new
            {
                Success = true,
                Message = "All Order items returned.",
                orders
            });
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return StatusCode(500, ex.Message);
        }
    }

    [HttpGet]
    [Route("withproducts")]
    public async Task<ActionResult<List<Order>>> GetAllWithProdcuts()
    {
        try
        {
            //var Data = _orderDBDapper.GetAllWithBooks();
            List<Order> orders = await _db.Orders.Include(o => o.Products).ToListAsync();

            return Ok(new
            {
                Success = true,
                Message = "All Order items returned.",
                orders
            });
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return StatusCode(500, ex.Message);
        }
    }

    [HttpGet]
    [Route("{orderGuid}")]
    public async Task<ActionResult<Order>> GetByOrderGuid(Guid orderGuid)
    {
        try
        {
            //var order = await _ordersDapper.GetByOrderGuid(orderGuid);
            Order order = await _db.Orders.Include(o => o.Products).Where(o => o.OrderGuid == orderGuid).FirstOrDefaultAsync();

            if (order == null) return NotFound();

            return Ok(new
            {
                Success = true,
                Message = "One Order item returned.",
                order
            });
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return StatusCode(500, ex.Message);
        }
    }

    [HttpPost]
    public async Task<IActionResult> Create(OrderDTO orderDTO)
    {
        //return Ok("hello");
        string userGuid = "E8E369C0-960B-4584-9A81-F9FF9F98DBD6";
        var hh = HttpContext;

        try
        {
            Order orderFinal = _mapper.Map<Order>(orderDTO);
            orderFinal.UserGuid = new Guid(userGuid);
            orderFinal.CreatedDate = DateTime.Now;

            _db.Orders.Add(orderFinal);
            await _db.SaveChangesAsync();

            return Ok(new
            {
                Success = true,
                Message = "Order created.",
                UserGuid = orderFinal.UserGuid
            });

        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return StatusCode(500, ex.Message);
        }
    }

    [HttpDelete]
    [Route("{OrderGuid}")]
    public async Task<IActionResult> Delete(Guid orderGuid)
    {
        try
        {
            if (await _db.Orders.Include(o => o.Products).Where(o => o.OrderGuid == orderGuid).FirstOrDefaultAsync() is Order order)
            {
                Console.WriteLine(order.BasketGuid);
                Console.WriteLine(order.Products);

                // first remove each book
                foreach (Product product in order.Products)
                {
                    _db.Products.Remove(product);
                }

                // you have to do this save outside of the loop, or it gets confused after deleting the first book
                await _db.SaveChangesAsync();

                _db.Orders.Remove(order);
                await _db.SaveChangesAsync();

                return Ok(new
                {
                    Success = true,
                    Message = "Order deleted."
                });
            }
            else
            {
                return Ok("nothing to delete");
            }

        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.ToString());
            return StatusCode(500, ex.Message);
        }
    }
}



