package ca.sheridancollege.menegonj.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ca.sheridancollege.menegonj.beans.User;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ca.sheridancollege.menegonj.beans.CPU;
import ca.sheridancollege.menegonj.beans.CartItem;
import ca.sheridancollege.menegonj.beans.GraphicsCard;
import ca.sheridancollege.menegonj.beans.Memory;
import ca.sheridancollege.menegonj.beans.Motherboard;
import ca.sheridancollege.menegonj.beans.Storage;

@Configuration
@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Methods for CPU
	public List<CPU> getAllCPUs() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM cpu";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(CPU.class));
	}

	public CPU getCPUById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "SELECT * FROM cpu WHERE id = :id";
		return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(CPU.class));
	}

	public void addCPU(CPU cpu) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("model", cpu.getModel());
		namedParameters.addValue("manufacturer", cpu.getManufacturer());
		namedParameters.addValue("price", cpu.getPrice());
		namedParameters.addValue("description", cpu.getDescription());
		namedParameters.addValue("speedGHz", cpu.getSpeedGHz());

		String query = "INSERT INTO cpu (model, manufacturer, price, description, speedGHz) "
				+ "VALUES (:model, :manufacturer, :price, :description, :speedGHz)";

		jdbc.update(query, namedParameters);
	}

	public void updateCPU(CPU cpu) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", cpu.getId()); // Assuming 'id' is the primary key

		namedParameters.addValue("model", cpu.getModel());
		namedParameters.addValue("manufacturer", cpu.getManufacturer());
		namedParameters.addValue("price", cpu.getPrice());
		namedParameters.addValue("description", cpu.getDescription());
		namedParameters.addValue("speedGHz", cpu.getSpeedGHz());

		String query = "UPDATE cpu SET model = :model, manufacturer = :manufacturer, price = :price, description = :description, speedGHz = :speedGHz "
				+ "WHERE id = :id";

		jdbc.update(query, namedParameters);
	}

	public void deleteCPU(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "DELETE FROM cpu WHERE id = :id";
		jdbc.update(query, namedParameters);
	}

	// Methods for GPU
	public List<GraphicsCard> getAllGPUs() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM graphics_card";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(GraphicsCard.class));
	}

	public GraphicsCard getGraphicsCardById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "SELECT * FROM graphics_card WHERE id = :id";
		return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(GraphicsCard.class));
	}

	public void addGraphicsCard(GraphicsCard graphicsCard) {
		String query = "INSERT INTO graphics_card (model, manufacturer, price, description, vramGB) "
				+ "VALUES (:model, :manufacturer, :price, :description, :vramGB)";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("model", graphicsCard.getModel());
		parameters.addValue("manufacturer", graphicsCard.getManufacturer());
		parameters.addValue("price", graphicsCard.getPrice());
		parameters.addValue("description", graphicsCard.getDescription());
		parameters.addValue("vramGB", graphicsCard.getVramGB());

		jdbc.update(query, parameters);
	}

	public void updateGraphicsCard(GraphicsCard graphicsCard) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", graphicsCard.getId());

		namedParameters.addValue("model", graphicsCard.getModel());
		namedParameters.addValue("manufacturer", graphicsCard.getManufacturer());
		namedParameters.addValue("price", graphicsCard.getPrice());
		namedParameters.addValue("vramGB", graphicsCard.getVramGB());

		String query = "UPDATE graphics_card SET model = :model, manufacturer = :manufacturer, "
				+ "price = :price, vramGB = :vramGB WHERE id = :id";

		jdbc.update(query, namedParameters);
	}

	public void deleteGraphicsCard(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "DELETE FROM graphics_card WHERE id = :id";
		jdbc.update(query, namedParameters);
	}

	// Memory related methods
	public List<Memory> getAllMemories() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM memory";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Memory.class));
	}

	public Memory getMemoryById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "SELECT * FROM memory WHERE id = :id";
		return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(Memory.class));
	}

	public void addMemory(Memory memory) {
		String query = "INSERT INTO memory (model, manufacturer, price, sizeGB, type, description) "
				+ "VALUES (:model, :manufacturer, :price, :sizeGB, :type, :description)";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("model", memory.getModel());
		parameters.addValue("manufacturer", memory.getManufacturer());
		parameters.addValue("price", memory.getPrice());
		parameters.addValue("sizeGB", memory.getSizeGB());
		parameters.addValue("type", memory.getType());
		parameters.addValue("description", memory.getDescription()); // Include description

		jdbc.update(query, parameters);
	}

	public void updateMemory(Memory memory) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", memory.getId());

		namedParameters.addValue("model", memory.getModel());
		namedParameters.addValue("manufacturer", memory.getManufacturer());
		namedParameters.addValue("price", memory.getPrice());
		namedParameters.addValue("sizeGB", memory.getSizeGB());
		namedParameters.addValue("type", memory.getType());

		String query = "UPDATE memory SET model = :model, manufacturer = :manufacturer, "
				+ "price = :price, sizeGB = :sizeGB, type = :type WHERE id = :id";

		jdbc.update(query, namedParameters);
	}

	public void deleteMemory(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "DELETE FROM memory WHERE id = :id";
		jdbc.update(query, namedParameters);
	}

	// Methods for Motherboard
	public List<Motherboard> getAllMotherboards() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM motherboard";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Motherboard.class));
	}

	public Motherboard getMotherboardById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "SELECT * FROM motherboard WHERE id = :id";
		return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(Motherboard.class));
	}

	public void addMotherboard(Motherboard motherboard) {
		String query = "INSERT INTO motherboard (model, manufacturer, price, description, socketType) "
				+ "VALUES (:model, :manufacturer, :price, :description, :socketType)";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("model", motherboard.getModel());
		parameters.addValue("manufacturer", motherboard.getManufacturer());
		parameters.addValue("price", motherboard.getPrice());
		parameters.addValue("description", motherboard.getDescription());
		parameters.addValue("socketType", motherboard.getSocketType());

		jdbc.update(query, parameters);
	}

	public void updateMotherboard(Motherboard motherboard) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", motherboard.getId());

		namedParameters.addValue("model", motherboard.getModel());
		namedParameters.addValue("manufacturer", motherboard.getManufacturer());
		namedParameters.addValue("price", motherboard.getPrice());
		namedParameters.addValue("socketType", motherboard.getSocketType());

		String query = "UPDATE motherboard SET model = :model, manufacturer = :manufacturer, "
				+ "price = :price, socketType = :socketType WHERE id = :id";

		jdbc.update(query, namedParameters);
	}

	public void deleteMotherboard(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "DELETE FROM motherboard WHERE id = :id";
		jdbc.update(query, namedParameters);
	}

	// Methods for Storage
	public List<Storage> getAllStorage() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM storage";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Storage.class));
	}

	public Storage getStorageById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "SELECT * FROM storage WHERE id = :id";
		return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(Storage.class));
	}

	public void addStorage(Storage storage) {
		String query = "INSERT INTO storage (model, manufacturer, price, description, capacityGB, type) "
				+ "VALUES (:model, :manufacturer, :price, :description, :capacityGB, :type)";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("model", storage.getModel());
		parameters.addValue("manufacturer", storage.getManufacturer());
		parameters.addValue("price", storage.getPrice());
		parameters.addValue("description", storage.getDescription());
		parameters.addValue("capacityGB", storage.getCapacityGB());
		parameters.addValue("type", storage.getType());

		jdbc.update(query, parameters);
	}

	public void updateStorage(Storage storage) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", storage.getId());

		namedParameters.addValue("model", storage.getModel());
		namedParameters.addValue("manufacturer", storage.getManufacturer());
		namedParameters.addValue("price", storage.getPrice());
		namedParameters.addValue("capacityGB", storage.getCapacityGB());
		namedParameters.addValue("type", storage.getType());

		String query = "UPDATE storage SET model = :model, manufacturer = :manufacturer, "
				+ "price = :price, capacityGB = :capacityGB, type = :type WHERE id = :id";

		jdbc.update(query, namedParameters);
	}

	public void deleteStorage(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "DELETE FROM storage WHERE id = :id";
		jdbc.update(query, namedParameters);
	}

	// Get all hardware for cart related activities
	// Only use if the switch statement in cart controller does not work
//	public HardwareItem getHardwareItemByTypeAndId(String hardwareType, Long itemId) {
//		// Implement logic to fetch hardware item by type and ID
//		// Example: if (hardwareType.equals("CPU")) { return getCPUById(itemId); }
//
//		// Return the appropriate hardware item based on type and ID
//		return null; // Modify this line to return the fetched item
//	}
	
//	public List<CartItem> getAllCartItems() {
//	    List<CartItem> cartItems = new ArrayList<>();
//
//	    // Implement logic to fetch all cart items from the database
//	    // Use appropriate SQL queries to retrieve cart items from the database tables
//	    // For example, if you have separate tables for different cart item types:
//	    // cartItems.addAll(getAllCartItemsCPU());
//	    // cartItems.addAll(getAllCartItemsGPU());
//	    // ... (similarly for other hardware types)
//
//	    return cartItems;
//	}

	// Cart Related Methods
	// Add to cart methods for each component (this might change into a super class
	// thing there is a lot of redundant code here
	public void addToCartCPU(CPU cpu) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", cpu.getId());
		namedParameters.addValue("model", cpu.getModel());
		namedParameters.addValue("manufacturer", cpu.getManufacturer());
		namedParameters.addValue("price", cpu.getPrice());
		namedParameters.addValue("description", cpu.getDescription());
		namedParameters.addValue("speedGHz", cpu.getSpeedGHz());

		String query = "INSERT INTO cart_cpu (id, model, manufacturer, price, description, speedGHz) "
				+ "VALUES (:id, :model, :manufacturer, :price, :description, :speedGHz)";

		jdbc.update(query, namedParameters);
	}

	public void addToCartGraphicsCard(GraphicsCard graphicsCard) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", graphicsCard.getId());
		namedParameters.addValue("model", graphicsCard.getModel());
		namedParameters.addValue("manufacturer", graphicsCard.getManufacturer());
		namedParameters.addValue("price", graphicsCard.getPrice());
		namedParameters.addValue("vramGB", graphicsCard.getVramGB());

		String query = "INSERT INTO cart_graphics_card (id, model, manufacturer, price, vramGB) "
				+ "VALUES (:id, :model, :manufacturer, :price, :vramGB)";

		jdbc.update(query, namedParameters);
	}

	public void addToCartMemory(Memory memory) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", memory.getId());
		namedParameters.addValue("model", memory.getModel());
		namedParameters.addValue("manufacturer", memory.getManufacturer());
		namedParameters.addValue("price", memory.getPrice());
		namedParameters.addValue("sizeGB", memory.getSizeGB());
		namedParameters.addValue("type", memory.getType());

		String query = "INSERT INTO cart_memory (id, model, manufacturer, price, sizeGB, type) "
				+ "VALUES (:id, :model, :manufacturer, :price, :sizeGB, :type)";

		jdbc.update(query, namedParameters);
	}

	public void addToCartMotherboard(Motherboard motherboard) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", motherboard.getId());
		namedParameters.addValue("model", motherboard.getModel());
		namedParameters.addValue("manufacturer", motherboard.getManufacturer());
		namedParameters.addValue("price", motherboard.getPrice());
		namedParameters.addValue("socketType", motherboard.getSocketType());

		String query = "INSERT INTO cart_motherboard (id, model, manufacturer, price, socketType) "
				+ "VALUES (:id, :model, :manufacturer, :price, :socketType)";

		jdbc.update(query, namedParameters);
	}

	public void addToCartStorage(Storage storage) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", storage.getId());
		namedParameters.addValue("model", storage.getModel());
		namedParameters.addValue("manufacturer", storage.getManufacturer());
		namedParameters.addValue("price", storage.getPrice());
		namedParameters.addValue("capacityGB", storage.getCapacityGB());
		namedParameters.addValue("type", storage.getType());

		String query = "INSERT INTO cart_storage (id, model, manufacturer, price, capacityGB, type) "
				+ "VALUES (:id, :model, :manufacturer, :price, :capacityGB, :type)";

		jdbc.update(query, namedParameters);
	}

	// User-related methods
	// Security Configuration Code

	// addUser Code
	public void addUser(String userName, String email, String password) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "INSERT INTO sec_user (userName, email, encryptedPassword, enabled) VALUES (:userName, :email, :encryptedPassword, 1)";
		namedParameters.addValue("userName", userName);
		namedParameters.addValue("email", email);
		namedParameters.addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, namedParameters);
	}

	// Method to find a user account by email
	public User findUserAccount(String userName) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where userName = :userName";
		namedParameters.addValue("userName", userName);

		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(User.class));

		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	public void addRole(Long userId, Long roleId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "INSERT INTO user_role (userId, roleId) " + "VALUES (:userId, :roleId)";
		namedParameters.addValue("userId", userId);
		namedParameters.addValue("roleId", roleId);
		jdbc.update(query, namedParameters);

	}

	// Method to get User Roles for a specific User ID
	public List<String> getRolesById(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT sec_role.roleName FROM user_role, sec_role WHERE user_role.roleId = sec_role.roleId AND userId = :userId";
		namedParameters.addValue("userId", userId);
		return jdbc.queryForList(query, namedParameters, String.class);
	}
}
