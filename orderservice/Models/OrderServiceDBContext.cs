using Microsoft.EntityFrameworkCore;

public class OrderServiceDBContext : DbContext
{
    public OrderServiceDBContext()
    {

    }

    public OrderServiceDBContext(DbContextOptions<OrderServiceDBContext> options) : base(options)
    {
    }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
    }

    public DbSet<User> Users { get; set; }
    public DbSet<Order> Orders { get; set; }
    public DbSet<Product> Products { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Order>().HasOne(o => o.User).WithMany(u => u.Orders).HasForeignKey(o => o.UserGuid);
        modelBuilder.Entity<Product>().HasOne(p => p.Order).WithMany(o => o.Products).HasForeignKey(p => p.OrderGuid);
    }
}