// Debug: 测试飞行物类型检测
import org.bukkit.entity.EntityType;

public class DebugProjectiles {
    public static void main(String[] args) {
        // 测试我们配置的飞行物类型
        String[] configuredTypes = {
            "ARROW", "SPECTRAL_ARROW", "SNOWBALL", "EGG", 
            "SPLASH_POTION", "LINGERING_POTION", "ENDER_PEARL", 
            "EYE_OF_ENDER", "FIREWORK_ROCKET", "TRIDENT"
        };
        
        System.out.println("配置的飞行物类型:");
        for (String type : configuredTypes) {
            try {
                EntityType entityType = EntityType.valueOf(type);
                System.out.println("✓ " + type + " -> " + entityType);
            } catch (IllegalArgumentException e) {
                System.out.println("✗ " + type + " 无效的实体类型!");
            }
        }
        
        // 检查常见的飞行物实体类型
        System.out.println("\n实际的飞行物实体类型:");
        EntityType[] projectileTypes = {
            EntityType.ARROW, EntityType.SPECTRAL_ARROW, EntityType.SNOWBALL,
            EntityType.EGG, EntityType.SPLASH_POTION, EntityType.LINGERING_POTION,
            EntityType.ENDER_PEARL, EntityType.ENDER_SIGNAL, EntityType.FIREWORK,
            EntityType.TRIDENT
        };
        
        for (EntityType type : projectileTypes) {
            System.out.println("- " + type.toString());
        }
    }
}
