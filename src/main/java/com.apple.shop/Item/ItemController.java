package com.apple.shop.item;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class ItemController {
  
  private final ItemRepository itemRepository;
  private final ItemService itemService;
  private final S3Service s3Service;

  @GetMapping("/write")
  String write() {
  return "write.html";
}


@GetMapping("/list")
String list(Model model){
  List<Item> result = itemRepository.findAll();
  model.addAttribute("items", result);
  return "list.html";
}


@PostMapping("/add")
String addPost(String title, Integer price) {
  itemService.saveItem(title, price);
  return "redirect:/list";
}


  
@GetMapping("/detail/{id}")
String detail(@PathVariable Long id, Model model) {

  CommentRepository.findAllByParentId(1L)
  
  Optional<Item> result = itemRepository.findById(id);
  if (result.isPresent()){
    model.addAttribute("data", result.get());
    return "detail.html";
    } else {
      return "redirect:/list";
    }
  }  

@GetMapping("/edit/{id}")
String edit(Model model, @PathVariable Long id) {
  Optional<Item> result = itemRepository.findById(id);
  if (result.isPresent()) {
    model.addAttribute("data", result.get());
    return "edit.html";
  } else {
    return "redirect:/list";
  }
}

  
@PostMapping("/edit")
String editItem(String title, Integer price, Long id) {
  Item item = new Item();
  item.setId(id);
  item.setTitle(title);
  item.setPrice(price);
  itemRepository.save(item);
  return "redirect:/list";
}
  
  
@DeleteMapping("/item")
ResponseEntity<String> deleteItem(@RequestParam Long id) {
  itemRepository.deleteById(id);
  return ResponseEntity.status(200).body("삭제완료");
} 

  
@GetMapping("/list/page/{abc}")
String getListPage(Model model, @PathVariable Integer abc){
  Page<Item> result = itemRepository.findPageBy(PageRequest.of(abc-1, 5));
  model.addAttribute("items", result);
  return "list.html";
}  
  

@GetMapping("/presigned-url")
@ResponseBody
String getURL(@RequestParam String filename){
  var result = s3Service.createPreSignedUrl("test/" + filename);
  System.out.println(result);
  return result;
}  

@PostMapping("/search")
String postSearch(@RequestParam String searchText){

  var result = itemRepository.rawQuery1(searchText);
  // var result = itemRepository.findAllByTitleContains(searchText);
  System.out.println(result);
  return "list.html";
}  


  // @Autowired
  // ItemController(ItemRepository itemRepository) {
  //   this.itemRepository = itemRepository;
  // }


// @PostMapping("/add")
// String writePost(String title, Integer price) {
//   itemService.saveItem(title, price);
//   return "redirect:/list";
// }


  
 
// @PostMapping("/add")
// String writePost(String title, Integer price) {
//   Item item = new Item;
//   item.setTitle(title);
//   item.setPrice(price);
//   itemRepository.save(item);
//   return "redirect:/list";
// }  


  

  




// var item = new Item();
// item.id = 1L;
// itemRepository.save(item)





// @GeteMapping("/test2")
// String deleteItem() {
//   var result = new BCryptPasswordEncorder().encode("문자");
//   System.out.println(result);
//   return "redirect:/list";
// }   


// @GetMapping("/detail/{id}")
// ResponseEntity<String> detail() {
//     try {
//       throw new Exception("이런저런에러");
//     } catch(Exception e){
//       return ResponseEntity.status(에러코드).body("에러이유");
//     }
// }


  
  
}






