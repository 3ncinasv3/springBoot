package ca.sheridancollege.menegonj.controllers;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ca.sheridancollege.menegonj.beans.CPU;
import ca.sheridancollege.menegonj.beans.GraphicsCard;
import ca.sheridancollege.menegonj.beans.Memory;
import ca.sheridancollege.menegonj.beans.Motherboard;
import ca.sheridancollege.menegonj.beans.Storage;
import ca.sheridancollege.menegonj.database.DatabaseAccess;

@Controller
public class AdminController {

	@Autowired
	private DatabaseAccess databaseAccess;

	// Landing page
	@GetMapping("/secure/admin/index")
	public String indexPage(Model model) {
		List<String> navigationLinks = Arrays.asList("CPU", "Graphics Card", "Memory", "Motherboard", "Storage");
		model.addAttribute("navigationLinks", navigationLinks);
		return "secure/admin/index";
	}

	// Display CPU list
	@GetMapping("/secure/admin/cpu")
	public String displayCPUList(Model model) {
		List<CPU> cpuList = databaseAccess.getAllCPUs();
		model.addAttribute("cpuList", cpuList);
		model.addAttribute("newCPU", new CPU()); // New empty CPU object for adding
		return "secure/admin/cpu";
	}

	// Add CPU
	@PostMapping("/secure/admin/addCPU")
	public String addCPU(@ModelAttribute CPU cpu) {
		databaseAccess.addCPU(cpu);
		return "redirect:/secure/admin/cpu"; // Redirect back to the CPU list
	}

	// Edit CPU
	@GetMapping("/secure/admin/editCPU/{id}")
	public String editCPU(@PathVariable Long id, Model model) {
		CPU cpu = databaseAccess.getCPUById(id);
		model.addAttribute("cpu", cpu);
		return "secure/admin/editCPU"; // Create this HTML page for editing CPU
	}

	// Update CPU
	@PostMapping("/secure/admin/updateCPU")
	public String updateCPU(@ModelAttribute CPU cpu) {
		databaseAccess.updateCPU(cpu);
		return "redirect:/secure/admin/cpu"; // Redirect back to the CPU list
	}

	// Delete CPU
	@GetMapping("/secure/admin/deleteCPU/{id}")
	public String deleteCPU(@PathVariable Long id) {
		databaseAccess.deleteCPU(id);
		return "redirect:/secure/admin/cpu"; // Redirect back to the CPU list
	}

	// GPU GOES HERE
	// Display Graphics Card list
	@GetMapping("/secure/admin/graphicscard")
	public String displayGraphicsCardList(Model model) {
		List<GraphicsCard> graphicsCardList = databaseAccess.getAllGPUs();
		model.addAttribute("graphicsCardList", graphicsCardList);
		model.addAttribute("newGraphicsCard", new GraphicsCard()); // For the add form
		return "secure/admin/graphicscard";
	}

	// Add Graphics Card
	@PostMapping("/secure/admin/addGraphicsCard")
	public String addGraphicsCard(@ModelAttribute GraphicsCard graphicsCard) {
		databaseAccess.addGraphicsCard(graphicsCard);
		return "redirect:/secure/admin/graphicscard";
	}

	// Edit Graphics Card
	@GetMapping("/secure/admin/editGraphicsCard/{id}")
	public String editGraphicsCard(@PathVariable Long id, Model model) {
		GraphicsCard graphicsCard = databaseAccess.getGraphicsCardById(id);
		model.addAttribute("graphicsCard", graphicsCard);
		return "secure/admin/editGraphicscard";
	}

	// Update Graphics Card
	@PostMapping("/secure/admin/updateGraphicsCard")
	public String updateGraphicsCard(@ModelAttribute GraphicsCard graphicsCard) {
		databaseAccess.updateGraphicsCard(graphicsCard);
		return "redirect:/secure/admin/graphicscard";
	}

	// Delete Graphics Card
	@GetMapping("/secure/admin/deleteGraphicsCard/{id}")
	public String deleteGraphicsCard(@PathVariable Long id) {
		databaseAccess.deleteGraphicsCard(id);
		return "redirect:/secure/admin/graphicscard";
	}

	// Display Memory list
	@GetMapping("/secure/admin/memory")
	public String displayMemoryList(Model model) {
		List<Memory> memoryList = databaseAccess.getAllMemories();
		model.addAttribute("memoryList", memoryList);
		model.addAttribute("newMemory", new Memory()); // To create a new Memory instance
		return "secure/admin/memory";
	}

	// Add Memory
	@PostMapping("/secure/admin/addMemory")
	public String addMemory(@ModelAttribute Memory memory) {
		databaseAccess.addMemory(memory);
		return "redirect:/secure/admin/memory";
	}

	// Edit Memory
	@GetMapping("/secure/admin/editMemory/{id}")
	public String editMemory(@PathVariable Long id, Model model) {
		Memory memory = databaseAccess.getMemoryById(id);
		model.addAttribute("memory", memory);
		return "secure/admin/editMemory";
	}

	// Update Memory
	@PostMapping("/secure/admin/updateMemory")
	public String updateMemory(@ModelAttribute Memory memory) {
		databaseAccess.updateMemory(memory);
		return "redirect:/secure/admin/memory";
	}

	// Delete Memory
	@GetMapping("/secure/admin/deleteMemory/{id}")
	public String deleteMemory(@PathVariable Long id) {
		databaseAccess.deleteMemory(id);
		return "redirect:/secure/admin/memory";
	}

	// Display Motherboard list
	@GetMapping("/secure/admin/motherboard")
	public String displayMotherboardList(Model model) {
		List<Motherboard> motherboardList = databaseAccess.getAllMotherboards();
		model.addAttribute("motherboardList", motherboardList);
		model.addAttribute("newMotherboard", new Motherboard()); // New Motherboard object for form
		return "secure/admin/motherboard"; // Create this HTML page
	}

	// Add Motherboard
	@PostMapping("/secure/admin/addMotherboard")
	public String addMotherboard(@ModelAttribute Motherboard motherboard) {
		databaseAccess.addMotherboard(motherboard);
		return "redirect:/secure/admin/motherboard"; // Redirect back to the Motherboard list
	}

	// Edit Motherboard
	@GetMapping("/secure/admin/editMotherboard/{id}")
	public String editMotherboard(@PathVariable Long id, Model model) {
		Motherboard motherboard = databaseAccess.getMotherboardById(id);
		model.addAttribute("motherboard", motherboard);
		return "secure/admin/editMotherboard"; // Create this HTML page for editing Motherboard
	}

	// Update Motherboard
	@PostMapping("/secure/admin/updateMotherboard")
	public String updateMotherboard(@ModelAttribute Motherboard motherboard) {
		databaseAccess.updateMotherboard(motherboard);
		return "redirect:/secure/admin/motherboard"; // Redirect back to the Motherboard list
	}

	// Delete Motherboard
	@GetMapping("/secure/admin/deleteMotherboard/{id}")
	public String deleteMotherboard(@PathVariable Long id) {
		databaseAccess.deleteMotherboard(id);
		return "redirect:/secure/admin/motherboard"; // Redirect back to the Motherboard list
	}

	// Display Storage list
	@GetMapping("/secure/admin/storage")
	public String displayStorageList(Model model) {
		List<Storage> storageList = databaseAccess.getAllStorage();
		model.addAttribute("storageList", storageList);
		model.addAttribute("newStorage", new Storage()); // for the add form
		return "secure/admin/storage"; // Create this HTML page
	}

	// Add Storage
	@PostMapping("/secure/admin/addStorage")
	public String addStorage(@ModelAttribute Storage storage) {
		databaseAccess.addStorage(storage);
		return "redirect:/secure/admin/storage"; // Redirect back to the Storage list
	}

	// Edit Storage
	@GetMapping("/secure/admin/editStorage/{id}")
	public String editStorage(@PathVariable Long id, Model model) {
		Storage storage = databaseAccess.getStorageById(id);
		model.addAttribute("storage", storage);
		return "secure/admin/editStorage"; // Create this HTML page for editing Storage
	}

	// Update Storage
	@PostMapping("/secure/admin/updateStorage")
	public String updateStorage(@ModelAttribute Storage storage) {
		databaseAccess.updateStorage(storage);
		return "redirect:/secure/admin/storage"; // Redirect back to the Storage list
	}

	// Delete Storage
	@GetMapping("/secure/admin/deleteStorage/{id}")
	public String deleteStorage(@PathVariable Long id) {
		databaseAccess.deleteStorage(id);
		return "redirect:/secure/admin/storage"; // Redirect back to the Storage list
	}

}
